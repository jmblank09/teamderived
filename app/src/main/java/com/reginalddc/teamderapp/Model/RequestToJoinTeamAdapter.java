package com.reginalddc.teamderapp.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.ManageFragment.RequestToJoinTeamFragment;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.ProfileFragment.OtherProfileFragment;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by reginalddc on 30/10/2016.
 */
public class RequestToJoinTeamAdapter extends ArrayAdapter<Team> {


    ProgressDialog prgDialog;

    public RequestToJoinTeamAdapter(Context context, ArrayList<Team> team){
        super(context, 0, team);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Team team = getItem(position);

        prgDialog = new ProgressDialog(getContext());
        prgDialog.setTitle("Processing...");
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_requesttojointeam, parent, false);
        }

        TextView viewProfile = (TextView)convertView.findViewById(R.id.btn_viewProfile);
        final FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                .beginTransaction();
        viewProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] user_id = TeamRequests.getUserID();
                OtherProfile.setUserID(user_id[position]);
                OtherProfile.setTracer(3);
                fragmentManager.replace(R.id.fragment_layout, new OtherProfileFragment()).commit();
            }
        });

        ImageView deleteMember = (ImageView)convertView.findViewById(R.id.btn_delete);
        deleteMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getContext());
                dlgAlert.setMessage("Are you sure you want to REMOVE this request?");
                dlgAlert.setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        });

                dlgAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        prgDialog.show();
                        RequestParams params = new RequestParams();
                        String[] request_id = TeamRequests.getRequestID();
                        String requestID = request_id[position];
                        params.put("request_id", requestID);
                        invokeDeleteWS(params);
                    }
                });

                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });

        ImageView approveMember = (ImageView)convertView.findViewById(R.id.btn_approve);
        approveMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getContext());
                dlgAlert.setMessage("Are you sure you want to CONFIRM this request?");
                dlgAlert.setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        });

                dlgAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        prgDialog.show();
                        RequestParams params = new RequestParams();
                        String[] request_id = TeamRequests.getRequestID();
                        String requestID = request_id[position];
                        params.put("request_id", requestID);
                        invokeAcceptWS(params);
                    }
                });

                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });

        TextView memName = (TextView) convertView.findViewById(R.id.textView_memName);
        TextView memDesc = (TextView) convertView.findViewById(R.id.textView_memDesc);

        memName.setText(team.teamName);
        memDesc.setText(team.teamDesc);

        return convertView;
    }

    private void invokeDeleteWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/requests/delete_request.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        Toast.makeText(getContext(), "Request has been removed", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();
                        FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                                .beginTransaction();
                        fragmentManager.replace(R.id.fragment_layout, new RequestToJoinTeamFragment()).commit();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();
                    }

                }catch (Exception e) {}
            }
        });
    }

    private void invokeAcceptWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/requests/confim_request.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        Toast.makeText(getContext(), "New member has been added to the team!", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();
                        FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                                .beginTransaction();
                        fragmentManager.replace(R.id.fragment_layout, new RequestToJoinTeamFragment()).commit();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();
                    }

                }catch (Exception e) {}
            }
        });
    }
}
