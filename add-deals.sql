select * from pg_replication_slots

select current_role;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO replicator;


insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 100000, 45000, 0);

insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 345678, 300500, 0);

insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 100000, 45000, 0);

insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 450000, 350000, 0);

insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 250000, 150000, 0);

insert into deals (id, tenant_id, created_by, created_date, updated_date, updated_by, details, name, status, company_id,
                   workspace_member_id, budget, estimated_costs, projected_profits)
values (nextval('seq_task_id'), 'FreeCRM', 'system', now(), now(), 'system', 'Details', 'My first deal', 'PROPOSED', 1, 1, 300000, 250000, 0);