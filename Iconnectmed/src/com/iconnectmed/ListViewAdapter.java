package com.iconnectmed;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<UserProfile> userProfileList = null;
    private ArrayList<UserProfile> arraylist;
    
    
    public ListViewAdapter(Context context,
            List<UserProfile> userProfileList) {
        this.context = context;
        this.userProfileList = userProfileList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<UserProfile>();
        this.arraylist.addAll(userProfileList);
    }
    
    public class ViewHolder {
        TextView textViewUserName;
        TextView textViewDepartment;
    }
 
    
	@Override
	public int getCount() {
		 return userProfileList.size();
	}
	@Override
	public Object getItem(int position) {
		return userProfileList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_user_list_item, null);
            // Locate the TextViews in listview_item.xml
            holder.textViewUserName = (TextView) convertView.findViewById(R.id.textViewName);
            holder.textViewDepartment = (TextView) convertView.findViewById(R.id.textViewDepartment);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        // Set the results into TextViews
        holder.textViewUserName.setText(userProfileList.get(position).getFullName());
        holder.textViewDepartment.setText(userProfileList.get(position).getDepartment());

        // Listen for ListView Item Click
        convertView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
            	
            	Log.d("ListViewAdapter", "clicked!!!!");
            	
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(context, SingleItemView.class);
//                // Pass all data rank
//                intent.putExtra("rank",
//                        (worldpopulationlist.get(position).getRank()));
//                // Pass all data country
//                intent.putExtra("country",
//                        (worldpopulationlist.get(position).getCountry()));
//                // Pass all data population
//                intent.putExtra("population",
//                        (worldpopulationlist.get(position).getPopulation()));
//                // Pass all data flag
//                intent.putExtra("flag",
//                        (worldpopulationlist.get(position).getFlag()));
//                // Start SingleItemView Class
//                context.startActivity(intent);
            }
        });
        return convertView;
    }
    
}
