package com.zaita.aliyounes.gsbvc2017.adapters;

        import android.graphics.Color;
        import android.os.Handler;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.zaita.aliyounes.gsbvc2017.R;
        import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
        import com.zaita.aliyounes.gsbvc2017.helpers.ColorHelper;
        import com.zaita.aliyounes.gsbvc2017.network.apis.ClientsNetworkCalls;
        import com.zaita.aliyounes.gsbvc2017.pojos.Client;

        import java.io.IOException;
        import java.net.SocketException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import io.reactivex.Observer;
        import io.reactivex.disposables.Disposable;

/**
 * Created by Ali Younes at 8/11/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientVieHolder> {

    //List of Clients to show
    private List<Client> clients;
    private List<Client> pendingClients;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r


    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
        this.pendingClients = new ArrayList<>();
    }

    @Override
    public ClientVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_client_layout, viewGroup , false);
        return new ClientVieHolder(view);
    }



    @Override
    public void onBindViewHolder(ClientVieHolder clientViewHolder, int position) {
        final Client client = clients.get(position);
        if (pendingClients.contains(client)) {
            // we need to show the "undo" state of the row
            clientViewHolder.itemView.setBackgroundColor(Color.RED);
            clientViewHolder.textView_clientEmail.setVisibility(View.GONE);
            clientViewHolder.textView_clientEmail_title.setVisibility(View.GONE);
            clientViewHolder.textView_clientMobile.setVisibility(View.GONE);
            clientViewHolder.textView_clientMobile_title.setVisibility(View.GONE);
            clientViewHolder.textView_clientTitle.setVisibility(View.GONE);
            clientViewHolder.textView_clientTitle_title.setVisibility(View.GONE);
            clientViewHolder.textView_clientName.setVisibility(View.GONE);
            clientViewHolder.textView_clientName_title.setVisibility(View.GONE);
            clientViewHolder.textView_clientTelephone.setVisibility(View.GONE);
            clientViewHolder.textView_clientTelephone_title.setVisibility(View.GONE);
            clientViewHolder.textView_clientAddresse.setVisibility(View.GONE);
            clientViewHolder.textView_clientAddresse_title.setVisibility(View.GONE);
            clientViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            clientViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            clientViewHolder.buttonUndo.setText(R.string.button_undo);
            clientViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(client.getID()));
                    pendingRunnables.remove(String.valueOf(client.getID()));
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    pendingClients.remove(client);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(clients.indexOf(client));
                }
            });
            if(client.isRemoving()) {
                //Deleting in progress
                clientViewHolder.itemView.setBackgroundColor(Color.GRAY);
                clientViewHolder.textView_clientName.setVisibility(View.GONE);
                clientViewHolder.textView_clientEmail.setVisibility(View.GONE);
                clientViewHolder.textView_clientEmail_title.setVisibility(View.GONE);
                clientViewHolder.textView_clientMobile.setVisibility(View.GONE);
                clientViewHolder.textView_clientMobile_title.setVisibility(View.GONE);
                clientViewHolder.textView_clientTitle.setVisibility(View.GONE);
                clientViewHolder.textView_clientTitle_title.setVisibility(View.GONE);
                clientViewHolder.textView_clientName.setVisibility(View.GONE);
                clientViewHolder.textView_clientName_title.setVisibility(View.GONE);
                clientViewHolder.textView_clientTelephone.setVisibility(View.GONE);
                clientViewHolder.textView_clientTelephone_title.setVisibility(View.GONE);
                clientViewHolder.textView_clientAddresse.setVisibility(View.GONE);
                clientViewHolder.textView_clientAddresse_title.setVisibility(View.GONE);
                clientViewHolder.buttonUndo.setVisibility(View.VISIBLE);
                clientViewHolder.buttonUndo.setText(R.string.deleting);
                clientViewHolder.buttonUndo.setEnabled(false);
            }
        } else {
            // we need to show the "normal" state
            clientViewHolder.itemView.setBackgroundColor(Color.WHITE);
            clientViewHolder.textView_clientEmail.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientEmail_title.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientMobile.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientMobile_title.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientTitle.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientTitle_title.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientName.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientName_title.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientTelephone.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientTelephone_title.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientAddresse.setVisibility(View.VISIBLE);
            clientViewHolder.textView_clientAddresse_title.setVisibility(View.VISIBLE);
            clientViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            clientViewHolder.bind(client);
            clientViewHolder.buttonUndo.setVisibility(View.GONE);
            clientViewHolder.buttonUndo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public void pendingRemoval(int position) {
        final Client client = clients.get(position);
        if (!pendingClients.contains(client)) {
            pendingClients.add(client);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(clients.indexOf(client));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(String.valueOf(client.getID()), pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        clients.get(position).setRemoving(true);
        notifyItemChanged(position);
        ClientsNetworkCalls.deleteClient(String.valueOf(String.valueOf(clients.get(position).getID()))).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Client client = clients.get(position);
                if (pendingClients.contains(client)) {
                    pendingClients.remove(client);
                }
                if (clients.contains(client)) {
                    clients.remove(position);
                    notifyItemRemoved(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Clients" , "Error getting Clients" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(GSBApplication.getInstance(), R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GSBApplication.getInstance() , e.getMessage() , Toast.LENGTH_LONG).show();
                }
                Client client = clients.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(client.getID()));
                pendingRunnables.remove(String.valueOf(client.getID()));
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingClients.remove(client);
                // this will rebind the row in "normal" state
                notifyItemChanged(clients.indexOf(client));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public boolean isPendingRemoval(int position) {
        Client item = clients.get(position);
        return pendingClients.contains(item);
    }
    
    class ClientVieHolder extends RecyclerView.ViewHolder {

        private TextView textView_clientEmail;
        private TextView textView_clientEmail_title;
        private TextView textView_clientMobile;
        private TextView textView_clientMobile_title;
        private TextView textView_clientTitle;
        private TextView textView_clientTitle_title;
        private TextView textView_clientName;
        private TextView textView_clientName_title;
        private TextView textView_clientTelephone;
        private TextView textView_clientTelephone_title;
        private TextView textView_clientAddresse;
        private TextView textView_clientAddresse_title;
        private Button   buttonUndo;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_client;

        ClientVieHolder(View itemView) {
            super(itemView);
            textView_clientName = (TextView) itemView.findViewById(R.id.textView_nomClient);
            textView_clientName_title = (TextView) itemView.findViewById(R.id.textView_nomClient_titre);
            textView_clientTelephone = (TextView) itemView.findViewById(R.id.textView_telClient);
            textView_clientTelephone_title = (TextView) itemView.findViewById(R.id.textView_telClient_titre);
            textView_clientAddresse = (TextView) itemView.findViewById(R.id.textView_adrClient);
            textView_clientAddresse_title = (TextView) itemView.findViewById(R.id.textView_adrClient_titre);
            textView_clientTitle = (TextView) itemView.findViewById(R.id.textView_titreClient);
            textView_clientTitle_title = (TextView) itemView.findViewById(R.id.textView_titreClient_titre);
            textView_clientMobile = (TextView) itemView.findViewById(R.id.textView_mobileClient);
            textView_clientMobile_title = (TextView) itemView.findViewById(R.id.textView_mobileClient_titre);
            textView_clientEmail = (TextView) itemView.findViewById(R.id.textView_emailClient);
            textView_clientEmail_title = (TextView) itemView.findViewById(R.id.textView_email_titre);
            buttonUndo = (Button) itemView.findViewById(R.id.undo_button);
            cardView_client = (CardView) itemView.findViewById(R.id.cardView_client);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Client client) {
            textView_clientAddresse.setText(client.getAddressClt());
            textView_clientName.setText(client.getNameClt());
            textView_clientTelephone.setText(client.getTelClt());
            textView_clientEmail.setText(client.getEmailClt());
            textView_clientMobile.setText(client.getMobClt());
            textView_clientTitle.setText(client.getTitleClt());
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for client item click
                }
            });
        }
    }
}
