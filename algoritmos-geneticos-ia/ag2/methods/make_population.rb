# Makes the initial population according to the tpop size
#
# @param tpop       [Integer] the size of the population
# @param positions  [Integer] the number of indexes (cities) possible
# @return           [Array]   the population
def make_population(tpop, positions)
  Array.new(tpop) { Individual.new(positions) }
end
