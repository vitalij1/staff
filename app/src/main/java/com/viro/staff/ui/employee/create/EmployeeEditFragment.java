package com.viro.staff.ui.employee.create;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.viro.staff.R;
import com.viro.staff.StaffApplication;
import com.viro.staff.annotation.LayoutId;
import com.viro.staff.bus.event.GoBackEvent;
import com.viro.staff.data.entity.Employee;
import com.viro.staff.ui.common.BaseFragment;
import com.viro.staff.ui.custom.dialog.YearPickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@LayoutId(R.layout.fragment_edit_employee)
public class EmployeeEditFragment extends BaseFragment<EmployeeEditPresenter> implements EmployeeEditPresenter.EmployeeEditView {
    private static final String TAG_EMPLOYEE_ID = "employeeId";
    private static final String[] positions = new String[] {"Врач", "Фармацевт", "Медсестра", "Водитель"};
    private static final String[] cities = new String[] {"Киев", "Харьков", "Одесса", "Львов"};

    @BindView(R.id.firstName)
    EditText firstName;
    @BindView(R.id.lastName)
    EditText lastName;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.position)
    Spinner position;
    @BindView(R.id.birthdayYear)
    EditText year;

    @Inject
    Bus bus;

    private int employeeId;

    public static EmployeeEditFragment newInstance(int employeeId) {
        Bundle args = new Bundle();
        args.putInt(TAG_EMPLOYEE_ID, employeeId);
        EmployeeEditFragment fragment = new EmployeeEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaffApplication.getComponent(getActivity()).inject(this);
        employeeId = getArguments().getInt(TAG_EMPLOYEE_ID);
        year.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - YearPickerDialog.YEAR_MIN_DIFF));
        initSpinners();
        getActivity().setTitle(employeeId != 0 ? "Изменить" : "Создать");
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadEmployee(employeeId);
    }

    private void initSpinners() {
        SpinnerAdapter positionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, positions);
        SpinnerAdapter cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cities);
        position.setAdapter(positionAdapter);
        city.setAdapter(cityAdapter);
    }

    @Override
    public void onEmployeeLoaded(Employee employee) {
        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        year.setText(String.valueOf(employee.getYear()));
        selectSpinnerValue(positions, employee.getPosition(), position);
        selectSpinnerValue(cities, employee.getCity(), city);
    }

    private void selectSpinnerValue(String[] items, String value, Spinner spinner) {
        int index = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(value)) {
                index = i;
                break;
            }
        }
        spinner.setSelection(index);
    }

    @Override
    public void onSaved() {
        bus.post(new GoBackEvent());
        Toast.makeText(getActivity(), "Employee saved successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public EmployeeEditPresenter createPresenter() {
        return new EmployeeEditPresenterImpl(this);
    }

    @OnClick(R.id.save)
    public void onSaveClick() {
        Employee employee = new Employee.Builder()
                .id(employeeId)
                .city(city.getSelectedItem().toString())
                .position(position.getSelectedItem().toString())
                .firstName(firstName.getText().toString())
                .lastName(lastName.getText().toString())
                .year(year.getText().toString().isEmpty() ? 0 : Integer.parseInt(year.getText().toString()))
                .build();
        getPresenter().saveEmployee(employee);
    }

    @OnClick(R.id.cancel)
    public void onCancelClick() {
        bus.post(new GoBackEvent());
    }

    @OnClick(R.id.birthdayYear)
    public void onYearPick() {
        YearPickerDialog yearPickerDialog = new YearPickerDialog();
        yearPickerDialog.show(getFragmentManager(), "YearPicker");
        yearPickerDialog.setListener((datePicker, i, i1, i2) -> year.setText(String.valueOf(i)));
    }
}
