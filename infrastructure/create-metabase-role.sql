create role metabase login password 'metabase';
grant connect on database "free-crm" to metabase;
grant select on all tables in schema public to metabase;
grant select on all sequences in schema public to metabase;
revoke create on schema public from metabase;