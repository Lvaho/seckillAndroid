/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfirechat.push;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by heavyrain.lee on 2018/3/18.
 */

public class AndroidPushMessage implements Parcelable {
    public String sender;
    public String senderName;
    public int convType;
    public String target;
    public String targetName;
    public int line;
    public int cntType;
    public long serverTime;
    public int pushMessageType; //0 normal, 1 voip invite, 2 voip bye
    public String pushContent;
    public String pushData;
    public int unReceivedMsg;
    public int mentionedType;
    public boolean isHiddenDetail;
    public long messageId;

    protected AndroidPushMessage(Parcel in) {
        sender = in.readString();
        senderName = in.readString();
        convType = in.readInt();
        target = in.readString();
        targetName = in.readString();
        line = in.readInt();
        cntType = in.readInt();
        serverTime = in.readLong();
        pushMessageType = in.readInt();
        pushContent = in.readString();
        pushData = in.readString();
        unReceivedMsg = in.readInt();
        mentionedType = in.readInt();
        isHiddenDetail = in.readInt()>0;
        messageId = in.readLong();
    }

    public static final Creator<AndroidPushMessage> CREATOR = new Creator<AndroidPushMessage>() {
        @Override
        public AndroidPushMessage createFromParcel(Parcel in) {
            return new AndroidPushMessage(in);
        }

        @Override
        public AndroidPushMessage[] newArray(int size) {
            return new AndroidPushMessage[size];
        }
    };

    public AndroidPushMessage() {

    }

    public static AndroidPushMessage messageFromJson(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        AndroidPushMessage pushMessage = new AndroidPushMessage();
        pushMessage.sender = jsonObject.getString("sender");
        pushMessage.senderName = jsonObject.optString("senderName");
        pushMessage.convType = jsonObject.getInt("convType");
        pushMessage.target = jsonObject.getString("target");
        pushMessage.targetName = jsonObject.optString("targetName");
        pushMessage.line = jsonObject.optInt("line");
        pushMessage.cntType = jsonObject.optInt("cntType");
        pushMessage.serverTime = jsonObject.getLong("serverTime");
        pushMessage.pushMessageType = jsonObject.getInt("pushMessageType");
        pushMessage.pushContent = jsonObject.optString("pushContent");
        pushMessage.pushData = jsonObject.optString("pushData");
        pushMessage.unReceivedMsg = jsonObject.optInt("unReceivedMsg", 1);
        pushMessage.mentionedType = jsonObject.optInt("mentionedType", 0);
        pushMessage.isHiddenDetail = jsonObject.optBoolean("isHiddenDetail");
        pushMessage.messageId = jsonObject.optLong("messageId");
        return pushMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sender);
        dest.writeString(senderName);
        dest.writeInt(convType);
        dest.writeString(target);
        dest.writeString(targetName);
        dest.writeInt(line);
        dest.writeInt(cntType);
        dest.writeLong(serverTime);
        dest.writeInt(pushMessageType);
        dest.writeString(pushContent);
        dest.writeString(pushData);
        dest.writeInt(unReceivedMsg);
        dest.writeInt(mentionedType);
        dest.writeInt(isHiddenDetail?1:0);
        dest.writeLong(messageId);
    }
}
