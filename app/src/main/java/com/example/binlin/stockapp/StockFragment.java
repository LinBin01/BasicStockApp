package com.example.binlin.stockapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binlin.stockapp.Iex_calls.Stock;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.binlin.stockapp.MainActivity.STOCK_KEY;

public class StockFragment extends Fragment {

    private Stock stock;
    @BindView(R.id.fragment_layout)
    protected ConstraintLayout layout;
    @BindView(R.id.name_textView)
    protected TextView nameTextView;
    @BindView(R.id.current_imageView)
    protected ImageView current;
    @BindView(R.id.previous_imageView)
    protected ImageView previous;
    @BindView(R.id.previous_one_imageView)
    protected ImageView previousOne;
    @BindView(R.id.previous_two_imageView)
    protected ImageView previousTwo;
    @BindView(R.id.previous_three_imageView)
    protected ImageView previousThree;
    @BindView(R.id.current_stock_textView)
    protected TextView currentStock;
    @BindView(R.id.previous_date_textView)
    protected TextView previousDate;
    @BindView(R.id.previous_one_date_textView)
    protected TextView previousOneDate;
    @BindView(R.id.previous_two_date_textView)
    protected TextView previousTwoDate;
    @BindView(R.id.previous_three_date_textView)
    protected TextView previousThreeDate;
    @BindView(R.id.previous_percent_textView)
    protected TextView previousPercent;
    @BindView(R.id.previous_percent_one_textView)
    protected TextView previousOnePercent;
    @BindView(R.id.previous_percent_two_textView)
    protected TextView previousTwoPercent;
    @BindView(R.id.previous_percent_three_textView)
    protected TextView previousThreePercent;
    @BindView(R.id.previous_stock_textView)
    protected TextView previousStock;
    @BindView(R.id.previous_stock_one_textView)
    protected TextView previousStockOne;
    @BindView(R.id.previous_stock_two_textView)
    protected TextView previousStockTwo;
    @BindView(R.id.previous_stock_three_textView)
    protected TextView previousStockThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_results, container, false);
        ButterKnife.bind(this,view);
        stock = getArguments().getParcelable(STOCK_KEY);

        return view;
    }

    public static StockFragment newInstance() {

        Bundle args = new Bundle();

        StockFragment fragment = new StockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        nameTextView.setText(stock.getQuote().getCompanyName());
        currentStock.setText(stock.getQuote().getCurrentPrice());
        if(stock.getQuote().getCurrentChange() > 0){
            current.setImageResource(R.drawable.green_up);
        }else{
            current.setImageResource(R.drawable.red_down);
        }
        setRest();
        setText();
    }

    private void setText() {
        previousDate.setText(stock.getChart().get(stock.getChart().size() -1).getDate());
        previousOneDate.setText(stock.getChart().get(stock.getChart().size() -2).getDate());
        previousTwoDate.setText(stock.getChart().get(stock.getChart().size() -3).getDate());
        previousThreeDate.setText(stock.getChart().get(stock.getChart().size() -4).getDate());
        previousStock.setText(Double.toString(stock.getChart().get(stock.getChart().size() -1).getValue()));
        previousStockOne.setText(Double.toString(stock.getChart().get(stock.getChart().size() -2).getValue()));
        previousStockTwo.setText(Double.toString(stock.getChart().get(stock.getChart().size() -3).getValue()));
        previousStockThree.setText(Double.toString(stock.getChart().get(stock.getChart().size() -4).getValue()));

    }

    private void setRest(){

        previousPercent.setText(Double.toString(100 * stock.getChart().get(stock.getChart().size() -1).getChangePercent()));
        if(stock.getChart().get(stock.getChart().size() - 1).getChangePercent() > 0){
            previous.setImageResource(R.drawable.green_up);
        }else{
            previous.setImageResource(R.drawable.red_down);
        }


        previousOnePercent.setText(Double.toString(100 * stock.getChart().get(stock.getChart().size() -2).getChangePercent()));
        if(stock.getChart().get(stock.getChart().size() - 2).getChangePercent() > 0){
            previousOne.setImageResource(R.drawable.green_up);
        }else{
            previousOne.setImageResource(R.drawable.red_down);
        }

        previousTwoPercent.setText(Double.toString(100 * stock.getChart().get(stock.getChart().size() -3).getChangePercent()));
        if(stock.getChart().get(stock.getChart().size() - 3).getChangePercent() > 0){
            previousTwo.setImageResource(R.drawable.green_up);
        }else{
            previousTwo.setImageResource(R.drawable.red_down);
        }

        previousThreePercent.setText(Double.toString(100 * stock.getChart().get(stock.getChart().size() -4).getChangePercent()));
        if(stock.getChart().get(stock.getChart().size() - 4).getChangePercent() > 0){
            previousThree.setImageResource(R.drawable.green_up);
        }else{
            previousThree.setImageResource(R.drawable.red_down);
        }
    }


}
