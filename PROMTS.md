16.03.2026 11:33 am
# PROMPTS.md

## 16.03.2026 14:05
Prompt:
"Привет! Давай сделаем веб-сервис 'Fixer'. Он будет принимать и обрабатывать заявки на ремонт. Сначала спроектируем архитектуру: роли диспетчер и мастер, заявки с полями clientName, phone, address, problemText, status, assignedTo, createdAt, updatedAt. Хочу, чтобы всё было понятно и расширяемо."

AI Response:
"План:
1. Backend: Spring Boot 3, Java 21, Spring Data JPA, H2/SQLite.
2. Frontend: Thymeleaf.
3. Entity: User (id, name, role), RepairRequest (id, clientName, phone, address, problemText, status, assignedTo, createdAt, updatedAt).
4. Service layer для бизнес-логики.
5. Контроллеры: RequestController, DispatcherController, MasterController.
6. Race condition защитить через atomic update.
7. Тесты: минимум 2 (создание заявки и race condition).
8. Seed пользователей: 1 диспетчер, 2 мастера.
9. Локальный запуск через mvn spring-boot:run.
10. Структура проекта: pom.xml, main class, Entity, Repository, Service, Controller, DTO, templates, data.sql, race_test.sh, README.md, DECISIONS.md, PROMPTS.md."

---

## 16.03.2026 14:25
Prompt:
"Хочу, чтобы проект выглядел коммерчески: чистая структура Controller → Service → Repository, DTO для передачи данных, audit log для истории изменений, понятные HTTP-коды и JSON ответы, UI читабельный. Сформируй пример полного Maven-проекта с зависимостями."

AI Response:
"- pom.xml с зависимостями Spring Boot Starter Web, JPA, Thymeleaf, H2, Starter Test.
- main class FixerAppApplication.
- Entity: User, RepairRequest.
- DTO: RepairRequestDTO.
- Service layer: RequestService с методами createRequest, takeRequest, completeRequest, assignToMaster, cancelRequest.
- Controller: RequestController, DispatcherController, MasterController с эндпоинтами.
- Audit log: Event entity или логирование действий в консоль.
- race_test.sh для проверки параллельного взятия заявки.
- Templates: create_request.html, dispatcher_dashboard.html, master_dashboard.html.
- README.md и DECISIONS.md."

---

## 16.03.2026 15:00
Prompt:
"Для race condition: если два мастера одновременно пытаются взять одну заявку, один получает успех, второй — отказ (HTTP 409). Реализация через JPA и @Transactional."

AI Response:
"Пример:
```java
@Transactional
public boolean takeRequest(Long id){
    RepairRequest r = repo.findById(id).orElseThrow();
    if(!r.getStatus().equals('assigned')){
        return false; // уже взята
    }
    r.setStatus('in_progress');
    repo.save(r);
    return true;
}

Контроллер возвращает HTTP 409, если второй мастер не успел взять заявку."

16.03.2026 15:30

Prompt:
"Хочу сиды для H2: 1 диспетчер, 2 мастера, несколько тестовых заявок."

AI Response:

insert into user(id,name,role) values (1,'dispatcher','dispatcher');
insert into user(id,name,role) values (2,'master1','master');
insert into user(id,name,role) values (3,'master2','master');

insert into repair_request(client_name,phone,address,problem_text,status,assigned_to)
values ('Иван','12345','ул. Первого Мая 1','Протечка крана','new',null);
insert into repair_request(client_name,phone,address,problem_text,status,assigned_to)
values ('Мария','67890','ул. Труда 12','Не греет вода','assigned',2);
16.03.2026 16:00

Prompt:
"Сделай минимальные Thymeleaf-шаблоны: форма создания заявки, панель диспетчера, панель мастера."

AI Response:
"- create_request.html: поля clientName, phone, address, problemText, кнопка submit.

dispatcher_dashboard.html: таблица заявок, фильтр по статусу, кнопки назначить/отменить.

master_dashboard.html: таблица назначенных заявок, кнопки взять в работу/завершить."

16.03.2026 16:20

Prompt:
"Добавь тесты: создание заявки и race condition."

AI Response:
"- CreateRequestTest: проверяет POST /request и сохранение в базе.

TakeRequestRaceTest: два параллельных POST /take/{id}, один success, второй HTTP 409."

16.03.2026 16:40

Prompt:
"Сделай README.md и DECISIONS.md, чтобы проект выглядел профессионально."

AI Response:
"README.md:

Запуск: mvn spring-boot:run

URL: http://localhost:8080

Тестовые пользователи

Проверка race_test.sh

DECISIONS.md:

Spring Boot для быстрого старта

DTO + сервисный слой для поддержки кода

Atomic update для защиты от race condition

Thymeleaf для быстрого UI

Audit log для истории действий

H2 для локального запуска

Тесты для проверки ключевых функций"

16.03.2026 17:00

Prompt:
"Финальный коммерческий вид: чистая структура пакетов, имена классов понятные, audit log выводится в консоль при смене статуса, race_test.sh готов, html аккуратные, минимальные CSS стили."

AI Response:
"Проект готов:

Пакеты: controller, service, repository, model, dto

Файлы: FixerAppApplication.java, RequestController.java, DispatcherController.java, MasterController.java, RequestService.java, User.java, RepairRequest.java, RepairRequestDTO.java

Templates: 3 html файла

data.sql для сидов

race_test.sh

README.md + DECISIONS.md

pom.xml с зависимостями Spring Boot, JPA, Thymeleaf, H2, тесты"