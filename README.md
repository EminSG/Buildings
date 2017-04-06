# Buildings
Программу «План здания».

1. Возможности
  1.1 Создание записей о новых этажах и помещениях на этажах.
  1.2 Редактирование параметров существующих этажей и помещений.
  1.3 Удаление существующих этажей и помещений.
  1.4 Просмотр всего списка помещений.
    1.4.1 С возможностью просмотра в списке, как всех помещений, так и помещений только выбранного этажа или офисов отдельного выбранного офиса.
    1.4.2 С возможность просмотра в списке этажа для выбранного помещения или всех офисов, содержащих выбранный офис.
    1.4.3 С возможностью просмотра в списке набора параметров помещений из общего набора параметров (см. ниже).
    1.4.4 С возможностью сортировки списка по полям «Площадь помещения», «Номер комнаты», «Кол-во пожарных датчиков».
    1.4.5 С возможностью фильтрации списка по полям «Площадь помещения», «Номер комнаты», «Кол-во пожарных датчиков».
  1.5 Быстрый поиск помещений (и этажей на котором они располагаются) по полям «Площадь помещения», «Номер комнаты», «Кол-во пожарных датчиков».
  1.6 Подсчет количества этажей и помещений, а также помещений указанной площади.
  1.7 Сохранение созданного плана здания в файл (формат файла выбирает или придумывает сам разработчик).
  1.8 Загрузка плана здания из ранее сохраненного файла.
  1.9 Необходимо соблюдать целостность данных, например этаж площадью 100 квадратных метров не может содержать 3 офиса каждый площадью 35 квадратных метров.

2. Типы помещений и их параметров, которые необходимо учитывать
  2.1. Этаж здания
  2.1.1.Площадь этажа – редактируемый параметр.
  2.1.2.Номер этажа (сочетание цифр и букв) – редактируемый параметр.
  2.1.3.Общее кол-во помещений на этаже – вычисляемый параметр.
  2.1.4.Количество помещений каждого типа на этаже – набор вычисляемых параметров.
  2.2. Типы помещений, располагающиеся на этаже
  
    2.2.1.Офис (все параметры редактируемые, если не указано другого).

      2.2.1.1. Площадь помещения.
      2.2.1.2. Номер комнаты (сочетание цифр и букв).
      2.2.1.3. Наличие пожарных датчиков.
      2.2.1.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
      2.2.1.5. Название компании.
      2.2.1.6. Аренда / собственность.
      2.2.1.7. Количество офисов в нутрии данного офиса – вычисляемый параметр.
  
    2.2.2.Переговорная (все параметры редактируемые).

      2.2.2.1. Площадь помещения.
      2.2.2.2. Номер комнаты (сочетание цифр и букв).
      2.2.2.3. Наличие пожарных датчиков.
      2.2.2.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
      2.2.2.5. Оборудовано аппаратурой для презентаций.
      2.2.2.6. Зарезервирована.

    2.2.3.Спец. помещение (все параметры редактируемые).
      2.2.3.1. Площадь помещения.
      2.2.3.2. Номер комнаты (сочетание цифр и букв).
      2.2.3.3. Наличие пожранных датчиков.
      2.2.3.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
      2.2.3.5. Тип (Серверная, склад, помещение охраны).
