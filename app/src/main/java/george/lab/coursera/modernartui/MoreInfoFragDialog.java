package george.lab.coursera.modernartui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ggalt66 on 10/6/2015.
 */
public class MoreInfoFragDialog extends DialogFragment {

    private Button btnNoThanks;
    private Button btnMoreInfo;
    private DialogFragment myDialog = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_info_dialog, container);

        btnMoreInfo = (Button) view.findViewById(R.id.btnOK);
        btnNoThanks = (Button) view.findViewById(R.id.btnNo);
        getDialog().setTitle("More Information");

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO fill in events to call browswer window with http://www.moma.org
                openWebPage("http://www.moma.org");
                myDialog.dismiss();
            }
        });

        btnNoThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        return view;
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}


//public static class ProgressDialogFragment extends DialogFragment {
//
//    public static ProgressDialogFragment newInstance() {
//        return new ProgressDialogFragment();
//    }
//
//    // Build ProgressDialog
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        //Create new ProgressDialog
//        final ProgressDialog dialog = new ProgressDialog(getActivity());
//
//        // Set Dialog message
//        dialog.setMessage("Activity Shutting Down.");
//
//        // Dialog will be displayed for an unknown amount of time
//        dialog.setIndeterminate(true);
//
//        return dialog;
//    }
//}