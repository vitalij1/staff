package com.viro.staff.ui.employee.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viro.staff.R;
import com.viro.staff.annotation.LayoutId;
import com.viro.staff.data.entity.Employee;
import com.viro.staff.ui.common.BaseFragment;
import com.viro.staff.ui.custom.decorator.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@LayoutId(R.layout.fragment_employee_list)
public class EmployeeListFragment extends BaseFragment<EmployeeListPresenterImpl> implements
        EmployeeListPresenter.EmployeeListView {

    @BindView(R.id.list)
    RecyclerView list;

    private EmployeeAdapter adapter;

    public static EmployeeListFragment newInstance() {
        Bundle args = new Bundle();
        EmployeeListFragment fragment = new EmployeeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        list.addItemDecoration(new SpacesItemDecoration(10));
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadItems();
        getActivity().setTitle("Сотрудники");
    }

    @Override
    public EmployeeListPresenterImpl createPresenter() {
        return new EmployeeListPresenterImpl(this);
    }

    @Override
    public void onEmployeeLoaded(List<Employee> items) {
        adapter = new EmployeeAdapter(items);
        list.setAdapter(adapter);
        adapter.setOnEmployeeClick(employee -> getPresenter().onEmployeeClick(getFragmentManager(), employee));
    }

    @OnClick(R.id.addButton)
    public void onAddClick() {
        getPresenter().onAddClick(getFragmentManager());
    }
}
