package com.guanyue.everydaynews.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by LiDaChang on 17/8/10.
 * __--__---__-------------__----__
 */

public class UserBean implements Parcelable, Serializable {

    private String id;          // 用户ID，服务ID
    private String token;       // token
    private String nickname;    // 用户昵称
    private String phone;       // 用户绑定手机号
    private int creditRating;   // 用户信用等级;
    private String photo;       // 用户头像url
    private String sign;        // 用户签名
    private int isFollow;       // 是否关注
    private int fansCount;      // 粉丝数
    private int productCount;   // 作品数
    private long costTime;      // 用户在作品消耗总时间,单位秒
    private long createTime;    // 用户初次创建时间
    private int isHasPassword;  // 是否有Passwd
    private int isAnchor;       // 是否是主播
    private String mSourceId;
    private int mSource;
    private long cost_time;   // 用户支付时间
    private static final long serialVersionUID = 6594145194559850545L;

    public long getCost_time() {
        return cost_time;
    }

    public void setCost_time(long cost_time) {
        this.cost_time = cost_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsAnchor() {
        return isAnchor;
    }

    public void setIsAnchor(int isAnchor) {
        this.isAnchor = isAnchor;
    }

    public UserBean() {
    }


    public UserBean(JSONObject obj) {
        if (obj != null) {
            id = obj.optString("id");
            token = obj.optString("token");
            nickname = obj.optString("nickname");
            photo = obj.optString("photo");
            phone = obj.optString("phone");
            if (phone != null) {
                setPhone(phone);//这里是为了加密
            }
            sign = obj.optString("sign");
            creditRating = obj.optInt("credit_rating");
            fansCount = obj.optInt("fans_count");
            productCount = obj.optInt("product_count");
            costTime = obj.optLong("cost_time");
            createTime = obj.optLong("time");

            // 是否是主播
            isAnchor = obj.optInt("is_anchor");
            // 是否有密码
            isHasPassword = obj.optInt("has_password");
            // 是否关注
            isFollow = obj.optInt("is_follow");
            mSourceId = obj.optString("source_id");
            mSource = obj.optInt("source", -1);
            cost_time = obj.optLong("cost_time");
        }
    }


    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public String getPhone() {
        return encodePhone(phone);
    }

    public void setPhone(String phone) {
        this.phone = encodePhone(phone);
    }

    public int getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean getIsHasPassword() {
        return isHasPassword != 0;
    }

    public void setIsHasPassword(int isHasPassword) {
        this.isHasPassword = isHasPassword;
    }

    public void setIsHasPassword(boolean isHasPassword) {
        this.isHasPassword = isHasPassword ? 1 : 0;
    }

    public boolean isFollow() {
        return isFollow != 0;
    }

    public void setFollow(int follow) {
        isFollow = follow;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    protected UserBean(Parcel in) {
        id = in.readString();
        token = in.readString();
        nickname = in.readString();
        sign = in.readString();
        isFollow = in.readInt();
        photo = in.readString();

        this.costTime = in.readLong();
        this.createTime = in.readLong();
        this.creditRating = in.readInt();
        this.fansCount = in.readInt();
        this.productCount = in.readInt();

        this.isFollow = in.readInt();
        this.isAnchor = in.readInt();
        this.isHasPassword = in.readInt();
        this.mSourceId = in.readString();
        this.mSource = in.readInt();
        this.cost_time = in.readLong();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getIs_follow() {
        return isFollow;
    }

    public void setIs_follow(int isFollow) {
        this.isFollow = isFollow;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(token);
        dest.writeString(nickname);
        dest.writeString(sign);
        dest.writeInt(isFollow);
        dest.writeString(photo);

        dest.writeLong(costTime);
        dest.writeLong(createTime);
        dest.writeInt(fansCount);
        dest.writeInt(productCount);
        dest.writeInt(creditRating);

        dest.writeInt(isFollow);
        dest.writeInt(isAnchor);
        dest.writeInt(isHasPassword);
        dest.writeString(mSourceId);
        dest.writeInt(mSource);
    }

    private String encodePhone(String phone) {
        int code = 112;
        char[] charArray = phone.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ code);
        }
        return new String(charArray);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phone + '\'' +
                ", creditRating=" + creditRating +
                ", photo='" + photo + '\'' +
                ", sign='" + sign + '\'' +
                ", isFollow=" + isFollow +
                ", fansCount=" + fansCount +
                ", productCount=" + productCount +
                ", costTime=" + costTime +
                ", createTime=" + createTime +
                ", isHasPassword=" + isHasPassword +
                ", isAnchor=" + isAnchor +
                '}';
    }
}
