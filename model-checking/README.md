Model Checking
===============

> Following notes are taken from the course: [Introduction to Model Checking by Prof. Joost-Pieterp Katoen](https://moves.rwth-aachen.de/teaching/ss-16/ss16introduction-to-model-checking/)


### Software Verification Techniques
1. Peer review
2. Testing


### Formal Methods
- applied maths for modelling and analysing systems


#### Formal Verification Techniques for Property P
##### Deductive methods
	* method: provide a formal `proof` that `P` holds
	* tool: theorem prover/proff assistant or proof checker
	* applicable if: system has form of mathematicl theory

##### Model checking 
	* method: `systematic check` on `P` in all states
	* tool: model checker
	* applicable if: system generates (finite) behavioral model

##### Model-based simulation or testing
	* method: test for `P` by `exploring possible behabiours`


---

* Symbolic model checking - a method of formally checking system designs

*What are Models?*
- Transition systems
	* states 
	* transition between states

*Temporal Logic*
	* Propositional logic
	* Model operators such as "always" and "eventually"
	* Interpreted over infinite state sequences (linear)
	* Or over infinite trees of states (branching)


### What are properties?
* Safety: something bad never happen
* Liveness: something good eventually happens
* Faireness: if something may happen frequently, it will happen




# References
1. [Introduction to Model Checking](https://moves.rwth-aachen.de/teaching/ss-16/ss16introduction-to-model-checking/) | [Videos](https://youtube.com/playlist?list=PLwabKnOFhE38C0o6z_bhlF_uOUlblDTjh)
2. [Advanced Model Checking](https://moves.rwth-aachen.de/teaching/ws-1617/advanced-model-checking/)