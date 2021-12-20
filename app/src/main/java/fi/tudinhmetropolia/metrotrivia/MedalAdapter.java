package fi.tudinhmetropolia.metrotrivia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MedalAdapter extends BaseAdapter {

    List<ModeResult> medalList; //List of all mode results

    public MedalAdapter(List<ModeResult> medalList) {
        this.medalList = medalList;
    }

    @Override
    public int getCount() {
        return medalList.size();
    } //adapter method to get the number of items in the data set, represented by this adapter. Source:https://developer.android.com/reference/android/widget/BaseAdapter

    @Override
    public Object getItem(int position) {
        return medalList.get(position);
    } //adapter method to get a specific item (a mode result) from the list

    @Override
    public long getItemId(int position) { //adapter method to get the row id associated with the specified position in the list.
        return position; //Purpose of id: https://stackoverflow.com/questions/5100071/whats-the-purpose-of-item-ids-in-android-listview-adapter
    }

    @SuppressLint("SetTextI18n")
    @Override
    /*Get a View that displays the data at the specified position in the data set. This method is
      called multiple times as the size of the provided list

    Source 1: https://developer.android.com/reference/android/widget/Adapter#getView(int,%20android.view.View,%20android.view.ViewGroup)
    Source 2: https://www.devglan.com/android/create-custom-adapter-in-list-view */

    public View getView(int position, View convertView, ViewGroup parent) {
    //Source 3: https://developer.android.com/reference/android/widget/ListView
        //parent: container of all items
        if (convertView == null) {
            /*Create a new View object from xml layout (lines 50-52)
            Source 4: https://stackoverflow.com/questions/3477422/what-does-layoutinflater-in-android-do */
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE); // = getLayoutInflater(). Source 5: https://stackoverflow.com/questions/7803771/call-to-getlayoutinflater-in-places-not-in-activity
            convertView = inflater.inflate(R.layout.medal_item_view, parent, false);
        }

        ModeResult modeResult = medalList.get(position); //Adapter reads the data to be shown at a given position (Lecture06_ListView_Adapter)

        //Find views from medal_item_view.xml
        TextView bronzeNumber = convertView.findViewById(R.id.bronze_number_text_view);
        TextView silverNumber = convertView.findViewById(R.id.silver_number_text_view);
        TextView goldNumber = convertView.findViewById(R.id.gold_number_text_view);
        TextView modeName = convertView.findViewById(R.id.mode_name_text_view);
        //Adapter provides content for a view at specified position (Display data item)
        bronzeNumber.setText(modeResult.getBronzeNumbers().toString());
        silverNumber.setText(modeResult.getSilverNumbers().toString());
        goldNumber.setText(modeResult.getGoldNumbers().toString());
        modeName.setText(modeResult.getModeName());

        return convertView; //convertView explanation: https://stackoverflow.com/questions/10560624/what-is-the-purpose-of-convertview-in-listview-adapter
    }
}
