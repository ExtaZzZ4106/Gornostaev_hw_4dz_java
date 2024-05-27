# Gornostaev_hw_4dz_java

*Репозиторий с 4-мя домашками.
Все задания распределены по отдельным веткам.*

1.Задание 1
##Сортировка способом шелла (Shell Sort)

- Принцип работы

    Код реализует сортировку методом Шелла. Этот метод является улучшенной версией сортировки вставками.
      Он осуществляет сравнение элементов, находящихся на определенном расстоянии друг от друга, 
      и перемещает их в нужное место. После этого шаг уменьшается вдвое, и процесс повторяется, 
      пока шаг не станет равным нулю. В итоге получается массив, отсортированный в порядке возрастания.
      
    В методе main создается массив чисел, выводится до начала сортировки и после нее.
      Функция sort_Shell осуществляет саму сортировку,
      а метод Arrays.stream используется для преобразования отсортированного массива в строку для вывода.

2.Задание 2
##Блакнот с защитой паролем

- Метод pass:

     Создает окно с полем для ввода пароля и кнопкой для его подтверждения.
      Если введенный пароль совпадает с предварительно установленным паролем, вызывает метод notePad, который открывает главное окно редактора.

- Метод notePad:

     Создает главное окно редактора с меню и вкладками для открытых файлов.
      Меню содержит опции для создания нового файла, открытия существующего файла и сохранения файла.
      При выборе опции "New file" добавляется новая вкладка с пустым текстовым полем.
      При выборе опции "Open file" открывается диалоговое окно выбора файла, после чего содержимое выбранного файла отображается в новой вкладке.
      При выборе опции "Save file" текст из текущей вкладки сохраняется в выбранный файл или создается новый файл для сохранения.
      Этот код представляет пример простого GUI-приложения с использованием библиотеки Swing для создания графического интерфейса пользователя.

3.Задание 3
      Простая база данных для ведения учёта расходов и доходов с таблицами для категорий, 
      транзакций и пользователей.

4.Задание 4
      Десктопное приложение для ведения учёта расходов и доходов с таблицами для категорий, 
      транзакций и пользователей на основе 3 задания.

- Этот код представляет собой простое приложение для входа в систему с 
- использованием графического интерфейса Swing и взаимодействия с базой данных MySQL.
  Вот краткое описание его работы:

- Класс login:

   Этот класс реализует окно для ввода имени пользователя и пароля для доступа к системе.
      При нажатии кнопки "Enter" происходит попытка подключения к базе данных MySQL с использованием введенного имени пользователя и пароля.
      Если подключение успешно, открывается новое окно (главное меню MainMenu), которое позволяет взаимодействовать с финансовой информацией.
      Если подключение не удалось, выводится сообщение об ошибке.

- Класс MainMenu:

   Этот класс представляет главное меню приложения, отображающее таблицы с данными о пользователях, категориях транзакций и транзакциях.
      Данные для таблиц просто заданы в виде двумерных массивов data, data1 и data2. (Данные таблици не являются основными
      я ещё не подключал бд к ним по этому пока что просто заполнил чтоб посмотреть как они работают)

- Класс main:

   Этот класс содержит метод main, который инициализирует главное меню и окно входа при запуске приложения.
      Этот код позволяет пользователям войти в систему и взаимодействовать с финансовой информацией через графический интерфейс.

### На данный момент дз ещё не доделанно оно до сих пор в разработке, 
### пока что отправил как есть, чуть позже допилю и закину изменения :)


