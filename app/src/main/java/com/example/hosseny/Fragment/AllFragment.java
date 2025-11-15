package com.example.hosseny.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hosseny.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    private RecyclerView recyclerView;
    private AllAdapter adapter;
    private List<String> items;

    public AllFragment() {
        // Required empty public constructor
    }

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate layout
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.all_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // بيانات تجريبية — بدّليها ببياناتك الحقيقية أو جيبيها من Firebase/DB
        items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        adapter = new AllAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    // Adapter داخلي بسيط
    static class AllAdapter extends RecyclerView.Adapter<AllAdapter.VH> {
        private final List<String> data;

        AllAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_allitem, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            String s = data.get(position);
            holder.title.setText(s);
            // مثال على click
            holder.itemView.setOnClickListener(v -> {
                // TODO: handling click, مثلاً فتح تفاصيل
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class VH extends RecyclerView.ViewHolder {
            TextView title;
            VH(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.item_title);
            }
        }
    }
}
