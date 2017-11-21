# Makes the graph for the problem. The array indexes start from 1
class Graph
  attr_reader :vertices

  # Initializes a hash of vertices
  def initialize
    @vertices = {}
  end

  # Adds a new weighted edge between source and destiny
  #
  # @param source  [Integer] source vertex
  # @param destiny [Integer] destiny vertex
  # @param weigth  [Float]   weigth of the connection
  def add_edge(source, destiny, weigth)
    source, destiny, weigth = convert_values(source, destiny, weigth)

    add_vertex(source)
    add_vertex(destiny)

    make_edge(source, destiny, weigth)
  end

  # -------------------------  PRIVATE METHODS
  private

  # Converts the value of the parameters, in order to normalize then
  #
  # @param source  [Integer] source vertex
  # @param destiny [Integer] destiny vertex
  # @param weigth  [Float]   weigth of the connection
  # @return        [Integer, Integer, Float] the converted values
  def convert_values(source, destiny, weigth)
    [Integer(source), Integer(destiny), Float(weigth)]
  end

  # Adds a new vertex to the hash, if it does exist already, nothing is done
  #
  # @param v [Integer] the vertex being added
  def add_vertex(v)
    array    = []
    array[v] = 0
    @vertices[v] ||= array
  end

  # Makes the bidirectional connection between the vertices
  #
  # @param source  [Integer] source vertex
  # @param destiny [Integer] destiny vertex
  # @param weigth  [Float]   weigth of the connection
  def make_edge(source, destiny, weigth)
    @vertices[source][destiny] = weigth
    @vertices[destiny][source] = weigth
  end
end
