package com.zaita.aliyounes.gsbvc2017.fragments;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.adapters.AvailabilitiesAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.pojos.Availability;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabilityFragment extends Fragment {

    public static final String TAG = AvailabilityFragment.class.getSimpleName();
    RecyclerView recyclerView_availabilities;
    AvailabilitiesAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    private List<Availability> availabilities;
    private CompositeDisposable compositeDisposable;

    public AvailabilityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_availabilities, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        availabilities = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if(GSBApplication.isDummyData()) {
            availabilities.addAll(getDummyAvailabilities());
            adapter.notifyDataSetChanged();
        } else {
            fetchAvailabilities();
        }
    }
    private void setupViews(View rootView) {
        recyclerView_availabilities = (RecyclerView) rootView.findViewById(R.id.recyclerView_availabilities);
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noAvailabilities);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new AvailabilitiesAdapter(availabilities);
        recyclerView_availabilities.setAdapter(adapter);
        recyclerView_availabilities.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
        recyclerView_availabilities.setHasFixedSize(true);
        setUpItemTouchHelper();
        setUpAnimationDecoratorHelper();
    }

    /**
     * This is the standard support library way of implementing "swipe to delete" feature. You can do custom drawing in onChildDraw method
     * but whatever you draw will disappear once the swipe is over, and while the items are animating to their new position the recycler view
     * background will be visible. That is rarely an desired effect.
     */
    private void setUpItemTouchHelper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_24dp);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) getContext().getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                AvailabilitiesAdapter testAdapter = (AvailabilitiesAdapter) recyclerView.getAdapter();
                if (testAdapter.isPendingRemoval(position)) {
                    return 0;
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                AvailabilitiesAdapter adapter = (AvailabilitiesAdapter) recyclerView_availabilities.getAdapter();
                boolean undoOn = true;
                if (undoOn) {
                    adapter.pendingRemoval(swipedPosition);
                } else {
                    adapter.remove(swipedPosition);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView_availabilities);
    }

    /**
     * We're gonna setup another ItemDecorator that will draw the red background in the empty space while the items are animating to thier new positions
     * after an item is removed.
     */
    private void setUpAnimationDecoratorHelper() {
        recyclerView_availabilities.addItemDecoration(new RecyclerView.ItemDecoration() {

            // we want to cache this and not allocate anything repeatedly in the onDraw method
            Drawable background;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                initiated = true;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                if (!initiated) {
                    init();
                }

                // only if animation is in progress
                if (parent.getItemAnimator().isRunning()) {

                    // some items might be animating down and some items might be animating up to close the gap left by the removed item
                    // this is not exclusive, both movement can be happening at the same time
                    // to reproduce this leave just enough items so the first one and the last one would be just a little off screen
                    // then remove one from the middle

                    // find first child with translationY > 0
                    // and last one with translationY < 0
                    // we're after a rect that is not covered in recycler-view views at this point in time
                    View lastViewComingDown = null;
                    View firstViewComingUp = null;

                    // this is fixed
                    int left = 0;
                    int right = parent.getWidth();

                    // this we need to find out
                    int top = 0;
                    int bottom = 0;

                    // find relevant translating views
                    int childCount = parent.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getLayoutManager().getChildAt(i);
                        if (child.getTranslationY() < 0) {
                            // view is coming down
                            lastViewComingDown = child;
                        } else if (child.getTranslationY() > 0) {
                            // view is coming up
                            if (firstViewComingUp == null) {
                                firstViewComingUp = child;
                            }
                        }
                    }

                    if (lastViewComingDown != null && firstViewComingUp != null) {
                        // views are coming down AND going up to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    } else if (lastViewComingDown != null) {
                        // views are going down to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = lastViewComingDown.getBottom();
                    } else if (firstViewComingUp != null) {
                        // views are coming up to fill the void
                        top = firstViewComingUp.getTop();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    }

                    background.setBounds(left, top, right, bottom);
                    background.draw(c);

                }
                super.onDraw(c, parent, state);
            }

        });
    }

    private void fetchAvailabilities() {
        /*progressBarLoadingData.setVisibility(View.VISIBLE);
          AvailabilitiesNetworkCalls.getAllAvailabilities().subscribe(new Observer<List<Availability>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Availability> value) {
                //Value is the return of the API call
                //In this case it is a list of availabilities
                //For more info see Mohammad faour's code (ManagedObjects/AvailabilityController.java)
                Log.i("Get availabilities" , value.size()+" Availability");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    availabilities.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(Vew.GONE);
                Log.e("Get availabilities" , "Error getting availabilities" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(getContext() , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.VISIBLE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext() , e.getMessage() , Toast.LENGTH_LONG).show();
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onComplete() {
                progressBarLoadingData.setVisibility(Vew.GONE);
            }
        });*/
    }

    //Create dummy branches
    private List<Availability> getDummyAvailabilities() {
        List<Availability> dummyAvailabilities = new ArrayList<>();
        dummyAvailabilities.add(new Availability("Branch 1" , "Product 1" , 123));
        dummyAvailabilities.add(new Availability("Branch 2" , "Product 2" , 123));
        dummyAvailabilities.add(new Availability("Branch 3" , "Product 3" , 123));
        dummyAvailabilities.add(new Availability("Branch 4" , "Product 4" , 123));
        dummyAvailabilities.add(new Availability("Branch 5" , "Product 5" , 123));
        dummyAvailabilities.add(new Availability("Branch 6" , "Product 6" , 123));
        return dummyAvailabilities;
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
