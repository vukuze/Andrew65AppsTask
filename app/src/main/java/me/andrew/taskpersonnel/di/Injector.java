package me.andrew.taskpersonnel.di;

/**
 * Предназначен для инжекта зависимостей Dagger.
 * Вызывается в конструкторе супер-класса.
 * Требуется переопределить в классах-наследниках.
 */

public interface Injector {
    void inject();
}
