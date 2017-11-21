require 'test/unit'
require_relative '../graph'

class TestGraph < Test::Unit::TestCase
  def test_vertices_should_be_empty
    graph = Graph.new
    assert_equal({}, graph.vertices)
  end

  def test_not_empty_vertices
    graph = Graph.new
    graph.add_edge(1, 2, 3.6)
    assert_equal(2, graph.vertices.size)
  end

  def test_same_weigth_for_the_egde
    graph = Graph.new
    graph.add_edge(1, 2, 3.6)
    assert_equal(3.6, graph.vertices[1][2])
    assert_equal(3.6, graph.vertices[2][1])
  end

  def test_cant_add_duplicate_vertices
    graph = Graph.new
    graph.add_edge(1, 2, 3.6)
    graph.add_edge(1, 2, 3.6)
    assert_equal(2, graph.vertices.size)
  end

  def test_values_converted
    graph = Graph.new
    graph.add_edge('1', '2', '3.6')
    assert_equal(Integer, graph.vertices.keys[0].class)
    assert_equal(Integer, graph.vertices.keys[1].class)
    assert_equal(Float,   graph.vertices[1][2].class)
  end
end
