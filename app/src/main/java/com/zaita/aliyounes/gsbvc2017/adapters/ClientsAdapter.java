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

        private TextView textView_clientEmail;
        private TextView textView_clientMobile;
        private TextView textView_clientTitle;
        private TextView textView_clientName;
        private TextView textView_clientTelephone;
        private TextView textView_clientAddresse;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_client;

        ClientVieHolder(View itemView) {
            super(itemView);
            textView_clientName = (TextView) itemView.findViewById(R.id.textView_nomClient);
            textView_clientTelephone = (TextView) itemView.findViewById(R.id.textView_telClient);
            textView_clientAddresse = (TextView) itemView.findViewById(R.id.textView_adrClient);
            textView_clientTitle = (TextView) itemView.findViewById(R.id.textView_titreClient);
            textView_clientMobile = (TextView) itemView.findViewById(R.id.textView_mobileClient);
            textView_clientEmail = (TextView) itemView.findViewById(R.id.textView_emailClient);
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
