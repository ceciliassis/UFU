module Tour
  # Picks the winning parent from the pair
  #
  # @param pop [Array]      population of individuals
  # @return    [Individual] the winning parent
  def self.pick_one(pop)
    pair = pick_two(pop)
    winner(*pair)
  end

  class << self
    private

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
  end
end
