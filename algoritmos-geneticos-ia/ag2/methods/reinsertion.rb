# Generates a new population combining parents and sons.
#
# @param tpop [Integer] the size of the population
# @param pop  [Array]   the old population
# @param sons [Array]   sons created from crossover
# @return     [Array]   the new populaiton
def reinsertion(tpop, pop, sons)
  everybody = pop + sons
  Array.new(tpop) { Tour.pick_one(everybody) }
end
