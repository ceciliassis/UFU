# classes
require_relative 'classes/graph'
require_relative 'classes/individual'
require_relative 'classes/extend_array'

# utils
require_relative 'util/read_input'

# methods
require_relative 'methods/make_population'
require_relative 'methods/calculate_fitness'
require_relative 'methods/selection'
require_relative 'methods/crossover'
require_relative 'methods/mutation'
require_relative 'methods/reinsertion'

GENS   = 300 # generations
TPOP   = 100 # population size
TCROSS = 40 * TPOP / 100
TMUT   = 0.03

graph = Graph.new
read_input(graph, 'in2')

positions  = graph.vertices[0].size
population = make_population(TPOP, positions)
calculate_fitness(population, graph)

best_one = population.sort[0]
count    = 0
when_    = -1
GENS.times do |i|
  parents = selection(TCROSS, population)
  sons    = crossover(parents)
  mutation(TMUT, sons)
  calculate_fitness(sons, graph)
  population = reinsertion(TPOP, population, sons)
  if best_one == population.sort[0]
    count += 1
  else
    best_one = population.sort[0]
    count    = 0
    when_    = i
  end
end

puts "Cities route: #{best_one}, fitness: #{best_one.fitness}."
puts "Achieved result in #{count} iterations, starting from #{when_ + 1}"
