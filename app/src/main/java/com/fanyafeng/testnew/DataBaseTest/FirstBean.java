package com.fanyafeng.testnew.DataBaseTest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2016/1/29,0029.
 */
public class FirstBean implements Parcelable {
    private String name;
    private String sex;
    private String address;
    private String phone;
    private int age;

    public FirstBean(String name, String sex, String address, String phone, int age) {
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "FirstBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeInt(age);
    }

    protected FirstBean(Parcel in) {
        name = in.readString();
        sex = in.readString();
        address = in.readString();
        phone = in.readString();
        age = in.readInt();
    }

    public static final Creator<FirstBean> CREATOR = new Creator<FirstBean>() {
        @Override
        public FirstBean createFromParcel(Parcel in) {
            return new FirstBean(in);
        }

        @Override
        public FirstBean[] newArray(int size) {
            return new FirstBean[size];
        }
    };

}
