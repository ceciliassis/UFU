# Calculates the fitness for the whole population
#
# @param population [Array] the current population
# @param graph      [Graph] the graph of cities
def calculate_fitness(population, graph)
  population.each do |ind|
    next if ind.fitness != Float::INFINITY
    ind.calculate_fitness(graph)
  end
end
