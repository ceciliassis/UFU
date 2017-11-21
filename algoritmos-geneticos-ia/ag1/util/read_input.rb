# Read the input from a file and constructs the graph
#
# @param graph [Graph]  empty graph
# @param input [String] the name of the input file.
def read_input(graph, input = 'in')
  File.open(input, 'r') do |file|
    file.each do |line|
      l = line.split
      graph.add_edge(l[0], l[1], l[2])
    end
  end
end
