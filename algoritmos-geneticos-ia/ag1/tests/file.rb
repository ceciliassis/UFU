ee = File.new('in2', 'w')

File.open('in', 'r') do |file|
  file.each do |line|
    l = line.split
    ee.puts "#{l[0].to_i - 1} #{l[1].to_i - 1} #{l[2]}"
  end
end
