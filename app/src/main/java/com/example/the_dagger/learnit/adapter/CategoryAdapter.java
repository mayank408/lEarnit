package com.example.the_dagger.learnit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.activity.QuizActivity;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by the-dagger on 1/10/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {
    private ArrayList<Categories> listCategories;
    private ArrayList<SingleChoiceQuestion> singleChoiceQuestionArrayList;
    private int position;
    //private final View.OnClickListener mOnClickListener  = new View.OnClickListener();
    private final Context context;
    int answer[] = new int[10];

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category_item, parent, false);
       // itemView.setOnClickListener(mOnClickListener);
        return new ViewHolder(itemView);
    }

    public CategoryAdapter(ArrayList<Categories> categories, Context context) {
        this.listCategories = categories;
        this.context = context;
        //singleChoiceQuestionArrayList = new ArrayList<>(categories.get(position).getQuizzes());
        //int i = 0;
        //while (i < singleChoiceQuestionArrayList.size()) {
//
        //    answer[i] = categories.get(position).getQuizzes().get(i).getAnswer();
        //    i++;
      //  }

    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, final int position) {
        this.position = position;
        Categories singleCategory = listCategories.get(holder.getAdapterPosition());
        if (getItemCount() == -1) {
            holder.title.setText("No Categories at the moment");
            holder.title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        } else {

            holder.title.setText(singleCategory.getName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("singleAdapter", listCategories.get(position));
                intent.putParcelableArrayListExtra("singleChoiceQuestion", listCategories.get(position).getQuizzes());
                intent.putExtra("answer", answer);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.category_title);
        }
    }

}
