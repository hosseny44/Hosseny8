package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hosseny.R;

public class Admin extends Fragment{

        private Button btnAdd, btnAll;
// LAMA
        public Admin() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_admin, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            btnAdd = view.findViewById(R.id.btnAddAdmin);
            btnAll = view.findViewById(R.id.btnAllAdmin);

            btnAdd.setOnClickListener(v -> gotoAddFragment());
            btnAll.setOnClickListener(v -> gotoAllFragment());
        }

        private void gotoAddFragment() {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new AddFragment()); // ضع هنا الفراجمنت الخاص بـ Add
            ft.addToBackStack(null); // optional, يسمح بالرجوع للـ AdminFragment
            ft.commit();
        }

        private void gotoAllFragment() {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new AllFragment()); // ضع هنا الفراجمنت الخاص بـ All
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}