# Selects tcross / 2 parents for the crossover
#
# @param tcross [Integer] rate of crossover
# @param pop    [Array]   population of individuals
# @return       [Array]   pairs of parents
def selection(tcross, pop)
  parents = {}
  while parents.size < (tcross / 2)
    p1 = pick_one(pop)
    p2 = pick_one(pop)
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

# Picks the winning parent from the pair
#
# @param pop [Array]      population of individuals
# @return    [Individual] the winning parent
def pick_one(pop)
  pair = pick_two(pop)
  winner(*pair)
end

# Randomly picks to individuals from the population
#
# @param pop [Array]                 population of individuals
# @return    [Individual, Indiviual] picked individuals
def pick_two(pop)
  op1 = pop[rand(pop.size)]
  op2 = pop[rand(pop.size)]
  [op1, op2]
end

# Returns the parent with the best fitness, the minimum one
#
# @param ind1 [Individual] some individual
# @param ind2 [Individual] some individual
# @return     [Individual] the individual with best fitness
def winner(ind1, ind2)
  (ind1 <=> ind2).zero? ? [ind1, ind2][rand(2)] : [ind1, ind2].min
end
