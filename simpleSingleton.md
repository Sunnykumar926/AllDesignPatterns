# Issue in this approach:

### 1. Not Thread Safe 
- Imagine two threads call `getInstance` at the same time then both execute at same time and create scenario like\n 
  **- Thread A checks → instance is null ✅**
  **- Thread B checks → instance is null ✅**
   - Then thread A creates object and thread B also creates another object.
- Now we have two objects which completely breaks the singleton guarantee.

### 2. Memory Leak Problem
- We are using `new` to allocate memory in the heap.but 
    - There is no destructor call 
    - No delete
    - Oject remains in heap forever

- In short-lived programs it doesn't matter much, but in real systems (servers, HFT systems and large apps) it's bad desgin.

### 3. Global State Problem
- Singleton is basically a controlled global variable and we know that issue with global state is 
    - Hard to test
    - Hard to mock
    - Tight coupling
    - Hidden dependencies
    - Not good for scalable system design
