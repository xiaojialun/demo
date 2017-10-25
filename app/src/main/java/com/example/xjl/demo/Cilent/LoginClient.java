package com.example.xjl.demo.Cilent;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.xjl.demo.IndexActivity;

import grpc.demo.login.LoginGreeterGrpc;
import grpc.demo.login.LoginReply;
import grpc.demo.login.LoginRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by xjl on 2017/10/25.
 */

public class LoginClient {
    private static final String TAG = "GrpcDemo";
    private static final int PROT = 50055;
    //    private static final String HOST = "192.168.199.157";
    private static final String HOST = "10.0.2.2";
    private Context context;

    public  LoginClient(String username,String password,Context context) {
        this.context=context;
        new LoginClient.GrpcTask(username,password).execute();
    }

    private class GrpcTask extends AsyncTask<Void, Void, LoginReply>{
        private String mUsername;
        private String mPassword;
        private String mHost;
        private int mPort;
        private ManagedChannel mChannel;

        public GrpcTask(String mUsername, String mPassword) {
            this.mUsername = mUsername;
            this.mPassword = mPassword;
            this.mHost=HOST;
            this.mPort=PROT;
        }

        @Override
        protected LoginReply doInBackground(Void... voids) {
            mChannel=ManagedChannelBuilder.forAddress(mHost,mPort)
                    .usePlaintext(true).build();
            LoginGreeterGrpc.LoginGreeterBlockingStub stub=LoginGreeterGrpc.newBlockingStub(mChannel);
            LoginRequest request=LoginRequest.newBuilder().setUsername(mUsername)
                    .setPassword(mPassword).build();
            LoginReply reply=stub.login(request);
            return reply;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(LoginReply reply) {
            if(reply.getLogin()){
                context.startActivity(new Intent(context, IndexActivity.class));
            }
            Log.d("GRPCDemo",reply.getMessage());
        }
    }
}
