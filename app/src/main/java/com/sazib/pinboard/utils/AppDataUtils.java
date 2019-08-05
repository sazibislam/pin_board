package com.sazib.pinboard.utils;

import android.content.Context;
import com.sazib.pinboard.R;
import com.sazib.pinboard.ui.pinboard.view.model.DataModel;
import java.util.ArrayList;

public class AppDataUtils {

  Context context;

  private AppDataUtils() {
  }

  public static ArrayList<DataModel> getPinBoardData() {

    ArrayList<DataModel> arrayList = new ArrayList<>();
    arrayList.add(new DataModel(R.drawable.profile, "Profile"));
    arrayList.add(new DataModel(R.drawable.profile, "Profile"));
    arrayList.add(new DataModel(R.drawable.profile, "Profile"));
    arrayList.add(new DataModel(R.drawable.profile, "Profile"));
    arrayList.add(new DataModel(R.drawable.profile, "Profile"));
    return arrayList;
  }
}
