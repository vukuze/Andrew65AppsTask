package com.example.andrew65appstask.ui;

/**
 * Предназначен для инжекта зависимостей Dagger.
 * Вызывается в конструкторе супер-класса.
 * Требуется переопределить в классах-наследниках.
 */

public interface Injector {
    void inject();
}
