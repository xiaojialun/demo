package com.example.xjl.demo.Cilent;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;
import grpc.demo.Client.SearchFriendGreeterGrpc;
import grpc.demo.Client.SearchFriendReply;
import grpc.demo.Client.SearchFriendRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by xjl on 2017/11/4.
 */

public class SearchFriendsClient {
    private static final String TAG = "GrpcDemo";
    private static final int PROT = 50055;
    //    private static final String HOST = "192.168.199.157";
//    private static final String HOST = "10.0.2.2";
    private static final String HOST="39.108.80.242";
    private Context context;

    public  SearchFriendsClient(Context context,String username) {
        this.context=context;
        new SearchFriendsClient.GrpcTask(username).execute();
    }

    private class GrpcTask extends AsyncTask<Void, Void, SearchFriendReply> {
        private String mHost;
        private int mPort;

        private String mUsername;

        private ManagedChannel mChannel;


        public GrpcTask(String username) {
            this.mHost = HOST;
            this.mPort = PROT;
            this.mUsername=username;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected SearchFriendReply doInBackground(Void... nothing) {
            try {
                mChannel = ManagedChannelBuilder.forAddress(mHost, mPort)
                        .usePlaintext(true)
                        .build();
                SearchFriendGreeterGrpc.SearchFriendGreeterBlockingStub stub = SearchFriendGreeterGrpc.newBlockingStub(mChannel);
                SearchFriendRequest message = SearchFriendRequest.newBuilder()
                        .setUsername(mUsername)
                        .build();
                SearchFriendReply reply = stub.searchFriend(message);
                return reply;
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                Log.d(TAG,"Failed... : " + sw);
                return null;
            }
        }

        @Override
        protected void onPostExecute(SearchFriendReply result) {
            if(result!=null){
                Log.d(TAG, result.getUser().toString());
            }else {

            }
            try {
                mChannel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}