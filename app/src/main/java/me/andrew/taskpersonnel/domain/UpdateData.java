package me.andrew.taskpersonnel.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.data.network.EmployeeRestAnswer;
import me.andrew.taskpersonnel.data.network.Response;
import me.andrew.taskpersonnel.data.network.SixtyFiveAppsRestService;
import me.andrew.taskpersonnel.util.JsonToEntityConverter;

/**
 * UseCase: запрос к серверу, очистка БД и обновление данных в ней
 */

public class UpdateData extends UseCase<UpdateData.RequestValues, Iterable<Employee>> {

    private SixtyFiveAppsRestService service;

    public UpdateData(Repository repository, SixtyFiveAppsRestService service) {
        super(repository);
        this.service = service;
    }

    @Override
    public Single<Iterable<Employee>> executeUseCase(final RequestValues values) {
        Log.d(this.getClass().getSimpleName(), "executeUseCase");
        return Single.just(Observable.empty())
                // delay используется, чтобы успеть увидеть выполнение операций
//                .delay(1, TimeUnit.SECONDS)
                .flatMap(emptyObservable -> getNetworkData())
//                .delay(1, TimeUnit.SECONDS)
                .map(response -> {
                    emptyDb();
                    return response;
                })
//                .delay(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.computation())
                .map(this::convertRestToDb)
//                .delay(1, TimeUnit.SECONDS)
                .flatMap(this::upsertDb)
                .cache();
    }

    /**
     * Подготовить REST данные к записи в БД, конвертируя формат ответа в формат сущности БД
     */
    private List<Employee> convertRestToDb(Response response) {
        Log.d(this.getClass().getSimpleName(), "map -> convert REST to DB");
        List<Employee> employees = new ArrayList<>();
        for (EmployeeRestAnswer employeeRestAnswer : response.getEmployees()) {
            Employee employee = JsonToEntityConverter.convertEmployee(employeeRestAnswer);
            employees.add(employee);
        }
        return employees;
    }

    /**
     * запрос к серверу и получение REST ответа
     */
    private Single<Response> getNetworkData() {
        Log.d(this.getClass().getSimpleName(), "flatMap -> getNetworkData");
        return Single.just(Observable.empty())
                .observeOn(Schedulers.io())
                .flatMap(unused -> service.getFile());
    }

    /**
     * Удалить все данные из БД
     */
    private void emptyDb() {
        Log.d(this.getClass().getSimpleName(), "emptyDb");
        repository.emptyDB();
    }

    /**
     * Обновляет данные в SQLite из REST ответа
     */
    private Single<Iterable<Employee>> upsertDb(List<Employee> employees) {
        Log.d(this.getClass().getSimpleName(), "flatMap -> upsertDb");
        return Single.just(employees)
                .observeOn(Schedulers.io())
                // запрос к серверу и получение REST ответа
                .flatMap(employeeList -> repository.upsert(employeeList));
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }
}
