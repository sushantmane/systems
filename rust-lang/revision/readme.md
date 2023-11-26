# A half hour to learn Rust... in one day

### Traits:
- something that multiple types can have in common
- Orphan Rules
  - can implement 
    - one of your traits on anyone's type
    - anyone's trait on one of your types
    - but not a foreign trait on a foreign type
 - `impl` block is always for a type; inside that block, `Self` refers to that type
 - Some `traits` are marker, i.e, they specify certain things can be done with a type
 - Trait methods can take `self` by reference or mutable reference