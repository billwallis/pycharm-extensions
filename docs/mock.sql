/* DuckDB */
with mock_data(id, name, date_of_birth, surname_ipa) as (
    values
        (1, 'William Shatner',  '1931-03-22', null),
        (2, 'Leonard Nimoy',    '1931-03-26', '/ˈniːmɔɪ/'),
        (3, 'Nichelle Nichols', '1932-12-28', '/nɪˈʃɛl/')
)

select
    cast(id as integer) as id,
    cast(name as text) as name,
    cast(date_of_birth as text) as date_of_birth,
    cast(surname_ipa as text) as surname_ipa
from mock_data
;
