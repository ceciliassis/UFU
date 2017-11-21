# Selects tcross / 2 parents for the crossover
#
# @param tcross [Integer] rate of crossover
# @param pop    [Array]   population of individuals
# @return       [Array]   pairs of parents
def selection(tcross, pop)
  parents = {}
  while parents.size < (tcross / 2)
    p1 = Tour.pick_one(pop)
    p2 = Tour.pick_one(pop)
    parents[[p1, p2]] = true unless already_in(parents, p1, p2)
  end
  parents.keys
end

# Verifies if some couple is already inside the chosen ones
#
# @param parents [Array]      pairs of parents already matched
# @param p1      [Individual] first parent
# @param p1      [Individual] second parent
# @return        [Boolean]    true if already in, false otherwise
def already_in(parents, p1, p2)
  !(parents[[p1, p2]].nil? && parents[[p2, p1]].nil?)
end
