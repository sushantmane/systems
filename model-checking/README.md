Model Checking
===============

> Following notes are taken from the course: [Introduction to Model Checking by Prof. Joost-Pieterp Katoen](https://moves.rwth-aachen.de/teaching/ss-16/ss16introduction-to-model-checking/)


# Introduction
--------------

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




# Transition Systems (TS)
-------

	* extended diagraphs

	```
		real systems

		|		   ^
		|		   |
	semantic      implementation 
	abstractions  refinement

		| 			|
		V  			|
		semantic model 

	```
	
	* Semantic model - a formal representation of
		- the states of the system
		- the stepwise behavior
		- the initial states
		- additional info on
			* communication  <-- actions
			* state properties <-- atomic proposition



# Modelling Parallel Systems
Concurrency
- no communication
- sync communication
- async communication (channel systems)
- full communication


#### Parallelism and Communication

Goal: define semantic parallel operators on transition systems or
program graphs that model "real" parallel operators
```
real parallel system
P = P1 || ... || Pn


transition system
T = T1 || ... || Tn
```


* Interleaving operator ||| for TS
	- interleaving of concurrent, independent actions of paralle processes
	- representation by nondeterministic choice: "which subprocess performs the next step?"
	- 



# References
1. [Introduction to Model Checking](https://moves.rwth-aachen.de/teaching/ss-16/ss16introduction-to-model-checking/) | [Videos](https://youtube.com/playlist?list=PLwabKnOFhE38C0o6z_bhlF_uOUlblDTjh)
2. [Advanced Model Checking](https://moves.rwth-aachen.de/teaching/ws-1617/advanced-model-checking/)