package com.bonson.qqtapk.model.data.contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.bonson.qqtapk.view.ui.info.select.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper {
	public static List<Select> contacts(Context context) {
		List<Select> list = new ArrayList<>();
		Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		if (cursor == null) {
			return list;
		}
		while (cursor.moveToNext()) {
			Select phone = contact(cursor);
			if (phone.getValue().matches("^(\\+86)?[0-9]{4,11}$")) {
				list.add(phone);
			}
		}
		return list;
	}

	public static Select contact(Cursor cursor) {
		Select phone = new Select();
		phone.setValue(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace(" ", ""));
		phone.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
		return phone;
	}
}
