require 'fc'

require_relative 'initial_state'
require_relative 'build_matrix'
require_relative 'moves'
require_relative 'extend_object_class'
require_relative 'accumulatted_manhattan'
require_relative 'path'
require_relative 'expand'
require_relative 'possible_solution'
require_relative 'run'

array = [7, 2, 4, 5, 0, 6, 8, 3, 1]
# array = initial_state

puts " Initial State:\n\n"
array.each_slice(array.size.sqrt) do |row|
  print '  '
  p row
end

unless possible_solution?(array)
  puts "\n No possible solution"
  return
end

puts "\n A Star Search:"
zero       = {}
matrix     = build_matrix(array, zero)
pq         = FastContainers::PriorityQueue.new(:min)
expected   = build_matrix([0, 1, 2, 3, 4, 5, 6, 7, 8], {}).flatten
vis_states = Hash.new(0x3f3f3f3f)

path          = Path.new
path.state    = matrix
path.zero_pos = zero

pq.push(path, path.path_value + accumulatted_manhattan(path.state, expected))

run(pq, path, expected, vis_states)
