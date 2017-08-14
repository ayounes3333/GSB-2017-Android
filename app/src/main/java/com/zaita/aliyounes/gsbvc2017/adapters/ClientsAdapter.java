package com.zaita.aliyounes.gsbvc2017.adapters;

        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.zaita.aliyounes.gsbvc2017.R;
        import com.zaita.aliyounes.gsbvc2017.helpers.ColorHelper;
        import com.zaita.aliyounes.gsbvc2017.pojos.Client;

        import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientVieHolder> {

    //List of Clients to show
    private List<Client> clients;

    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public ClientVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_client_layout, viewGroup , false);
        return new ClientVieHolder(view);
    }



    @Override
    public void onBindViewHolder(ClientVieHolder ClientVieHolder, int position) {
        Client client = clients.get(position);
        ClientVieHolder.bind(client);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    class ClientVieHolder extends RecyclerView.ViewHolder {

        private TextView textView_branchName;
        private TextView textView_branchTelephone;
        private TextView textView_branchAddresse;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_branch;

        ClientVieHolder(View itemView) {
            super(itemView);
            textView_branchName         = (TextView) itemView.findViewById(R.id.textView_nomBranch);
            textView_branchTelephone    = (TextView) itemView.findViewById(R.id.textView_telBranch);
            textView_branchAddresse     = (TextView) itemView.findViewById(R.id.textView_addrBranch);
            cardView_branch             = (CardView) itemView.findViewById(R.id.cardView_branch);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Client Client) {
            textView_branchAddresse.setText(Client.getAddressBr());
            textView_branchName.setText(Client.getNameBr());
            textView_branchTelephone.setText(Client.getTelBr());
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_branch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for branch item click
                }
            });
        }
    }
}
