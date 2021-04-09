package com.app.kucukbakkalapp.data.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Basket")
public class Basket {

    @PrimaryKey
    @ColumnInfo(name = "id")
    String id;

    @NonNull
    @ColumnInfo(name = "amount")
    Integer amount;

    public Basket() {
    }

    public Basket(String id, @NonNull Integer mAmount) {
        this.id = id;
        this.amount = mAmount;
    }


    @Ignore
    public Basket(@NonNull Integer mAmount) {
        this.amount = mAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Integer amount) {
        this.amount = amount;
    }
}
