package com.example.myapplication.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    Button buttonInsert,buttonUpdate,buttonClear,buttonDelete;
    RoomViewModel roomViewModel;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        roomViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(RoomViewModel.class);

        buttonInsert = this.findViewById(R.id.buttonInsert);
        buttonUpdate = this.findViewById(R.id.buttonUpdate);
        buttonClear = this.findViewById(R.id.buttonClear);
        buttonDelete = this.findViewById(R.id.buttonDelete);
        roomViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter.setAllWords(words);
                myAdapter.notifyDataSetChanged();
//                String text = "";
//                for(int i = 0; i < words.size(); i++){
//                    Word word = words.get(i);
//                    text += word.getId()+":"+word.getWord()+"="+word.getChineseMeaning()+"\n";
//                }
//                textView.setText(text);
            }
        });
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好！");
                Word word2 = new Word("Word","世界！");
                roomViewModel.insertWords(word1,word2);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomViewModel.deleteAllWords();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好-0-0-0！");
                word1.setId(31);
                roomViewModel.updateWords(word1);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好-0-0-0！");
                word1.setId(31);
                roomViewModel.deleteWords(word1);
            }
        });

    }

}