# PROMPT – Эпическая хроника разработки Fixer

## 16.03.2026 11:33 AM
**Marie Sok (PO/директор):**  
"Привет! Давай сделаем веб-сервис **Fixer**. Он должен принимать и обрабатывать заявки на ремонт. Спроектируй архитектуру: роли диспетчер и мастер, заявки с полями `clientName`, `phone`, `address`, `problemText`, `status`, `assignedTo`, `createdAt`, `updatedAt`. Всё должно быть расширяемым и понятным для будущих dev’ов."

**AI (разработчик):**  
"Понял! План:
1. Backend: Spring Boot 3, Java 21, Spring Data JPA, H2.
2. Frontend: Thymeleaf для минимального старта.
3. Entity: `User`, `RepairRequest`.
4. Service layer для бизнес-логики.
5. Контроллеры: `RequestController`, `DispatcherController`, `MasterController`.
6. Защита race condition через атомарные обновления.
7. Тесты: минимум два, включая race condition.
8. Seed: 1 диспетчер, 2 мастера.
9. Локальный запуск через `mvn spring-boot:run`.
10. Структура проекта: pom.xml, main class, Entity, Repository, Service, Controller, DTO, templates, data.sql, race_test.sh, README.md, DECISIONS.md, PROMPTS.md."

---

## 16.03.2026 14:25
**Marie Sok:**  
"Хочу коммерчески чистую архитектуру: Controller → Service → Repository, DTO для передачи данных, audit log для истории изменений, корректные HTTP-коды и JSON ответы. UI должен быть читабельным. Составь пример полного Maven-проекта с зависимостями."

**AI:**  
"Готово!
- pom.xml: Spring Boot Starter Web, JPA, Thymeleaf, H2, Test.
- Main class: `FixerAppApplication`.
- Entities: `User`, `RepairRequest`.
- DTO: `RepairRequestDTO`.
- Service layer: `RequestService` с методами `createRequest`, `takeRequest`, `completeRequest`, `assignToMaster`, `cancelRequest`.
- Контроллеры: `RequestController`, `DispatcherController`, `MasterController`.
- Audit log: логируем действия в консоль.
- race_test.sh для проверки параллельного взятия заявки.
- Templates: `create_request.html`, `dispatcher_dashboard.html`, `master_dashboard.html`.
- README.md и DECISIONS.md для документации."

---

## 16.03.2026 15:30

Marie Sok:
"Сиды для H2: 1 диспетчер, 2 мастера, несколько тестовых заявок."

AI:

INSERT INTO user_table(id,name,role) VALUES (1,'dispatcher','ADMIN');
INSERT INTO user_table(id,name,role) VALUES (2,'master1','MASTER');
INSERT INTO user_table(id,name,role) VALUES (3,'master2','MASTER');

INSERT INTO request(address,client_name,phone,description,status,assigned_to)
VALUES ('ул. Первого Мая 1','Иван','12345','Протечка крана','NEW',NULL);
INSERT INTO request(address,client_name,phone,description,status,assigned_to)
VALUES ('ул. Труда 12','Мария','67890','Не греет вода','NEW',NULL);
16.03.2026 16:00

Marie Sok:
"Минимальные Thymeleaf-шаблоны: форма заявки, панель диспетчера, панель мастера."

AI:
"- create_request.html: поля clientName, phone, address, problemText, кнопка submit.

dispatcher_dashboard.html: таблица заявок, фильтр по статусу, кнопки назначить/отменить.

master_dashboard.html: таблица назначенных заявок, кнопки взять в работу/завершить."

--- 

## 16.03.2026 16:20

Marie Sok:
"Добавь тесты: создание заявки и race condition."

AI:
"- CreateRequestTest: проверяет POST /request и сохранение в базе.

TakeRequestRaceTest: два параллельных POST /take/{id}, один success, второй HTTP 409."

---
##

16.03.2026 16:40

Marie Sok:
"Сделай README.md и DECISIONS.md, чтобы проект выглядел профессионально."

AI:
"- README.md: запуск, URL, тестовые пользователи, проверка race_test.sh.

DECISIONS.md: Spring Boot для быстрого старта, DTO + сервисный слой, атомарные обновления для защиты race condition, Thymeleaf для UI, audit log в консоль, H2 для локального запуска, тесты для проверки ключевых функций."

---

## 16.03.2026 17:00

Marie Sok:
"Финальный коммерческий вид: чистая структура пакетов, понятные имена классов, audit log выводится при смене статуса, race_test.sh готов, html аккуратные, минимал CSS."

AI:
"Проект готов:

Пакеты: controller, service, repository, model, dto.

Файлы: FixerAppApplication.java, RequestController.java, DispatcherController.java, MasterController.java, RequestService.java, User.java, RepairRequest.java, RepairRequestDTO.java.

Templates: 3 html файла.

data.sql для сидов.

race_test.sh.

README.md + DECISIONS.md.

pom.xml с зависимостями Spring Boot, JPA, Thymeleaf, H2, тесты."

---

## 16.03.2026 17:30

Marie Sok:
"Сделай фронт полностью минимал, современный Pinterest-style с чатиком поддержки, приятным пастельным фоном и кубиками для визуального интереса."

AI:
"Фронт готов:

Hover-эффекты, плавное появление карточек.

Чат поддержки в правом нижнем углу, не раздражает.

Форма создания заявки увеличивается при фокусе.

Цвета: пастельные, минимал.

Анимации плавные, интерфейс читабельный."

---

## 16.03.2026 18:00

Marie Sok:
"Добавь акции для новых клиентов."

AI:
"- Акция: 'первый вызов бесплатно' отображается на карточке новой заявки."