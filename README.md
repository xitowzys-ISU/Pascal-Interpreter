# Интерпретатор для "Pascal"

На основе кода с практических занятий разработать интерпретатор для упрощенной версии языка Pascal.

Интерпретатор должен выдавать значение всех переменных используемых в программе, например, в виде словаря. Дополнительно
реализовать возможность удаленного доступа к интерпретатору с использованием библиотеки zmq. Для удаленного случая
реализовать выбор выходных данных: результат в виде словаря со значениями переменных или исходного дерева на основе
которого считается выходной результат.

[Описание грамматики](https://gitlab.com/ISU-Applied-Computer-Science/5th-semester/theory-and-practice-of-programming-languages/Pascal-interpreter/-/blob/main/raw/pascal.pdf)

Общие требования:

* Должны быть написаны тесты для проверки всего кода
* Код может быть реализовано на одном из языков: Kotlin, Rust или Python.

## Демонстрация работы:

| [Провека готовых программ](https://gitlab.com/ISU-Applied-Computer-Science/5th-semester/theory-and-practice-of-programming-languages/Pascal-interpreter/-/tree/main/res/examples-programs) | 
| ----------- |
| ![](https://gitlab.com/ISU-Applied-Computer-Science/5th-semester/theory-and-practice-of-programming-languages/Pascal-interpreter/-/raw/main/raw/demo_1.png?inline=false) | 

| Coverage | 
| ----------- |
| ![](https://gitlab.com/ISU-Applied-Computer-Science/5th-semester/theory-and-practice-of-programming-languages/Pascal-interpreter/-/raw/main/raw/demo_2.png?inline=false) | 