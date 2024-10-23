package com.arinteck.planetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//Whenever you have any custom layouts you need to populate as done before.
//And I'm extending array adapter, not base adapter because I need to populate my custom model class
//And remember, whenever you have a model class, you need to store these objects in an array list and array
//So for that we use an array adapter and we passed planet and not string or int or primitive data.

public class CustomListAdapter extends ArrayAdapter<Planet> {
    private Context context;
    private ArrayList<Planet> planets;


    // Using Custom Layouts --> MyCustomAdapter
    // Using Custom Objects -> extends ArrayAdapter<Planet>

    public CustomListAdapter(Context context, ArrayList<Planet> planets){
        super(context, R.layout.item_list_layout, planets);
        this.context = context;
        this.planets = planets;
    }


    // View Holder Class: used to cache references to the views within
    // an item layout, so that they don't need to be repeatedly looked up during scrolling


    private  static  class  MyViewHolder{
        ImageView imageView;
        TextView platName, moonCount;
    }

    // getView(): used to create and return a view for a
    // specific item in the list.


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the planet object for the current position
        Planet planet = getItem(position);

        // innflate layout

        MyViewHolder myViewHolder;
        View result;

        if(convertView == null){// Create

            myViewHolder = new MyViewHolder();
            LayoutInflater inflater =  LayoutInflater.from(getContext());
            convertView =  inflater.inflate(
                    R.layout.item_list_layout,
                    parent, //This is used to correctly set layout parameters during inflation.
                    false // Passing false means that the view will not be attached to the parent right away,
                    // which is usually the desired behavior when you are providing the view to an adapter.
            );


            // Finding Views:
            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            myViewHolder.platName =  (TextView) convertView.findViewById(R.id.planet_name);
            myViewHolder.moonCount = (TextView) convertView.findViewById(R.id.planet_value);
            result =  convertView;

            //The sat tag method is used to attach  an arbitrary object to the view object.
            //This tag can be any object that you find useful for associating additional data or information with
            //By doing this, you are associating the Viewholder with the specific view that it represents
            //and the primary reason for attaching Viewholder to the view using set tag method is to ensure that you later
            //can retrieve it from the view  when you need to update the View's content,...

            convertView.setTag(myViewHolder);
         }else{
            // the view is recycled
            //When the view is recycled, you can retrieve the associated Viewholder using getTag() method and use
            //it to efficiently populate the view with the data,  so the getTag() method is used to
            //retrieve an object that was previously set as a tag using the set tag method on the view object.

            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;

        }

        assert planet != null;
        myViewHolder.platName.setText(planet.getPlanetName());
        myViewHolder.moonCount.setText(planet.getMoonCount());
        myViewHolder.imageView.setImageResource(planet.getPlanetImage());

        return  result;
    }
}
