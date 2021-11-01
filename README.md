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