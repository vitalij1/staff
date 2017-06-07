package com.viro.staff.ui.employee.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viro.staff.R;
import com.viro.staff.data.entity.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees = new ArrayList<>();
    private OnEmployeeClick onEmployeeClick;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void setOnEmployeeClick(OnEmployeeClick onEmployeeClick) {
        this.onEmployeeClick = onEmployeeClick;
    }

    public void setItems(List<Employee> items) {
        this.employees.clear();
        this.employees.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        Employee item = employees.get(position);
        holder.name.setText(item.getLastName() + " " + item.getFirstName().substring(0,1).toUpperCase() + ".");
        holder.age.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - item.getYear()));
        holder.city.setText(item.getCity());
    }

    @Override
    public int getItemCount() {
        return employees == null ? 0 : employees.size();
    }

    public interface OnEmployeeClick {
        void onEmployeeClick(Employee employee);
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.age)
        TextView age;

        @BindView(R.id.city)
        TextView city;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item)
        public void onItemClick() {
            if (onEmployeeClick != null) {
                onEmployeeClick.onEmployeeClick(employees.get(getLayoutPosition()));
            }
        }
    }
}
