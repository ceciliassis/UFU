class Individual
  include Comparable # inserts some methods like min and max

  attr_reader :fitness, :chromossome

  def initialize(positions, chr = nil)
    @chromossome = chr.nil? ? Array(0...positions).shuffle : chr
    @fitness     = Float::INFINITY
  end

  def <=>(other)
    @fitness <=> other.fitness
  end

  def size
    @chromossome.size
  end

  def to_s
    @chromossome.inspect
  end

  # Calculates the fitness for the individual
  #
  # @param graph      [Graph] the graph of cities
  def calculate_fitness(graph)
    distances = 0
    @chromossome.each_with_index do |citie, current|
      break if current + 1 >= size
      other_ind  = @chromossome[current + 1]
      distances += graph.vertices[citie][other_ind]
    end
    @fitness = distances
  end
end
