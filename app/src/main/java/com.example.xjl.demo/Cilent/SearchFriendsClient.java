package com.example.xjl.demo.Cilent;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


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
    //        private static final String HOST = "192.168.199.157";
    private static final String HOST = "10.0.2.2";
    //    private static final String HOST="39.108.80.242";
    private Context context;

    public SearchFriendsClient(Context context,String sUsername){
        this.context=context;
        new GrpcTask(sUsername);
    }

    private class GrpcTask extends AsyncTask<Void,Void,SearchFriendReply>{
        private ManagedChannel mChannel;
        private String mHost;
        private int mPort;
        private String sUsername;

        public GrpcTask(String sUsername){
            mHost=HOST;
            mPort=PROT;
            this.sUsername=sUsername;
        }

        @Override
        protected SearchFriendReply doInBackground(Void... voids) {
            mChannel= ManagedChannelBuilder.forAddress(mHost,mPort)
                    .usePlaintext(true).build();
            SearchFriendGreeterGrpc.SearchFriendGreeterBlockingStub stub=SearchFriendGreeterGrpc.newBlockingStub(mChannel);
            SearchFriendRequest request=SearchFriendRequest.newBuilder().setUsername(sUsername).build();
            SearchFriendReply reply=stub.searchFriend(request);
            Log.d("SearchResult is:",reply.getUser().toString());
            return reply;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(SearchFriendReply searchFriendReply) {
            super.onPostExecute(searchFriendReply);
        }
    }

}
