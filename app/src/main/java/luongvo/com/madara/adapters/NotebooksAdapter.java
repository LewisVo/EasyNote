package luongvo.com.madara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import luongvo.com.madara.R;
import luongvo.com.madara.database.DBHelper;
import luongvo.com.madara.model.Notebook;
import luongvo.com.madara.utils.Constants;

import com.squareup.picasso.Picasso;

import java.util.List;


public class NotebooksAdapter extends RecyclerView.Adapter<NotebooksAdapter.NotebookViewHolder> {

    private Context context;
    private List<Notebook> arrNotebooks;

    public NotebooksAdapter(Context context, List<Notebook> arrNotebooks) {
        this.context = context;
        this.arrNotebooks = arrNotebooks;
    }

    @Override
    public NotebookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notebook, parent, false);
        return (new NotebookViewHolder(itemView));
    }

    @Override
    public void onBindViewHolder(final NotebookViewHolder notebookViewHolder, int position) {
        final Notebook notebookResources = arrNotebooks.get(position);

        // TODO: notebookResources - onBindViewHolder - please notice the binding process
        /*
         * Add resources to notebookViewHolder's elements
         */
        String notebookTitle = notebookResources.getName();
        if (!(notebookTitle.length() <= Constants.maxTxtNotebookTitleLength)) {
            notebookTitle = notebookTitle.substring(0, Constants.maxTxtNotebookTitleLength);
        }

        notebookViewHolder.txtNotebookTitle.setText(notebookTitle);
        Picasso.with(context).load(notebookResources.getCover()).into(notebookViewHolder.imgNotebookCover);

        /*
         * Add events to notebookViewHolder's elements
         */
        notebookViewHolder.layoutNotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: show Notes in chosen Notebook
            }
        });
        notebookViewHolder.layoutNotebook.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toggleNotebookProperties(notebookViewHolder);
                return true;
            }
        });
        notebookViewHolder.btnNotebookInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: show Info of Notebook
            }
        });
        notebookViewHolder.btnNotebookLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: lock Notebook

            }
        });
        notebookViewHolder.btnNotebookDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Delete Notebook
                DBHelper.deleteNotebook(notebookResources);
                System.out.println("Notebook DELETED");
            }
        });
    }

    private void toggleNotebookProperties(NotebookViewHolder notebookViewHolder) {
        LinearLayout layoutNotebook = notebookViewHolder.layoutNotebook;
        /*
         * Constants to adjust rotating process
         */
        float rotationYValue = 25f;
        float translationXValue = 50f;
        float scaleXValue = 0.75f;
        float scaleYValue = 0.95f;
        if (layoutNotebook.getRotationY() == 0) {
            /*
             * Open Properties
             */
            layoutNotebook.animate().scaleX(scaleXValue).start();
            layoutNotebook.animate().scaleY(scaleYValue).start();
            layoutNotebook.animate().translationXBy(-translationXValue).start();
            layoutNotebook.animate().rotationY(rotationYValue).start();
        } else {
            /*
             * Close Properties
             */
            layoutNotebook.animate().scaleX(1f).start();
            layoutNotebook.animate().scaleY(1f).start();
            layoutNotebook.animate().translationXBy(translationXValue).start();
            layoutNotebook.animate().rotationY(0f).start();
        }
    }


    @Override
    public int getItemCount() {
        return arrNotebooks.size();
    }



    public class NotebookViewHolder extends RecyclerView.ViewHolder {
        public ImageView btnNotebookInfo, btnNotebookLock, btnNotebookDelete;
        public ImageView imgNotebookCover;
        public TextView txtNotebookTitle;
        public LinearLayout layoutNotebook;


        public NotebookViewHolder(View itemView) {
            super(itemView);
            btnNotebookInfo = (ImageView) itemView.findViewById(R.id.btnNotebookInfo);
            btnNotebookLock = (ImageView) itemView.findViewById(R.id.btnNotebookLock);
            btnNotebookDelete = (ImageView) itemView.findViewById(R.id.btnNotebookDelete);
            imgNotebookCover = (ImageView) itemView.findViewById(R.id.imgNotebookCover);
            txtNotebookTitle = (TextView) itemView.findViewById(R.id.txtNotebookTitle);
            layoutNotebook = (LinearLayout) itemView.findViewById(R.id.layoutNotebook);
        }
    }
}
