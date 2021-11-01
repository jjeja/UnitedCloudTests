# United cloud Tests

Test suite with selenium

### Code structure
there are 2 main packages `methods` and `selectors`.  
`selectors` contain all selectors needed for selenium.  
`methods` contain code needed for interactiong with elements.  
`tests` contain all test with assertions.

### Create Profile tests

`dataProviders` package contains providers for birth years and names.


There is JS error when tried to create profile without age or avatar:
`An invalid form control with name='avatar' is not focusable.`  
`An invalid form control with name='age' is not focusable.`  

SQL:

this is SQL queries for 4. question:

```
select avatar_id, count(avatar_id) as avatar_count from profile group by avatar_id order by avatar_count desc limit 1;

select identifier, head, body from avatar where id = from previous query;
```